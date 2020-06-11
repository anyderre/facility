import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFacility, Facility } from 'app/shared/model/facility.model';
import { FacilityService } from './facility.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-facility-update',
  templateUrl: './facility-update.component.html',
})
export class FacilityUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required]],
    area: [],
    quantity: [],
    creationDate: [],
    modificationDate: [],
    user: [],
  });

  constructor(
    protected facilityService: FacilityService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ facility }) => {
      if (!facility.id) {
        const today = moment().startOf('day');
        facility.creationDate = today;
        facility.modificationDate = today;
      }

      this.updateForm(facility);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(facility: IFacility): void {
    this.editForm.patchValue({
      id: facility.id,
      description: facility.description,
      area: facility.area,
      quantity: facility.quantity,
      creationDate: facility.creationDate ? facility.creationDate.format(DATE_TIME_FORMAT) : null,
      modificationDate: facility.modificationDate ? facility.modificationDate.format(DATE_TIME_FORMAT) : null,
      user: facility.user,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const facility = this.createFromForm();
    if (facility.id !== undefined) {
      this.subscribeToSaveResponse(this.facilityService.update(facility));
    } else {
      this.subscribeToSaveResponse(this.facilityService.create(facility));
    }
  }

  private createFromForm(): IFacility {
    return {
      ...new Facility(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      area: this.editForm.get(['area'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value
        ? moment(this.editForm.get(['creationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      modificationDate: this.editForm.get(['modificationDate'])!.value
        ? moment(this.editForm.get(['modificationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      user: this.editForm.get(['user'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFacility>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
