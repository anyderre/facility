import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IResident, Resident } from 'app/shared/model/resident.model';
import { ResidentService } from './resident.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IRoom } from 'app/shared/model/room.model';
import { RoomService } from 'app/entities/room/room.service';

type SelectableEntity = IUser | IRoom;

@Component({
  selector: 'jhi-resident-update',
  templateUrl: './resident-update.component.html',
})
export class ResidentUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  rooms: IRoom[] = [];

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    phoneNumber: [],
    email: [],
    creationDate: [],
    modificationDate: [],
    user: [],
    room: [],
  });

  constructor(
    protected residentService: ResidentService,
    protected userService: UserService,
    protected roomService: RoomService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resident }) => {
      if (!resident.id) {
        const today = moment().startOf('day');
        resident.creationDate = today;
        resident.modificationDate = today;
      }

      this.updateForm(resident);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.roomService.query().subscribe((res: HttpResponse<IRoom[]>) => (this.rooms = res.body || []));
    });
  }

  updateForm(resident: IResident): void {
    this.editForm.patchValue({
      id: resident.id,
      firstName: resident.firstName,
      lastName: resident.lastName,
      phoneNumber: resident.phoneNumber,
      email: resident.email,
      creationDate: resident.creationDate ? resident.creationDate.format(DATE_TIME_FORMAT) : null,
      modificationDate: resident.modificationDate ? resident.modificationDate.format(DATE_TIME_FORMAT) : null,
      user: resident.user,
      room: resident.room,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resident = this.createFromForm();
    if (resident.id !== undefined) {
      this.subscribeToSaveResponse(this.residentService.update(resident));
    } else {
      this.subscribeToSaveResponse(this.residentService.create(resident));
    }
  }

  private createFromForm(): IResident {
    return {
      ...new Resident(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      email: this.editForm.get(['email'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value
        ? moment(this.editForm.get(['creationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      modificationDate: this.editForm.get(['modificationDate'])!.value
        ? moment(this.editForm.get(['modificationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      user: this.editForm.get(['user'])!.value,
      room: this.editForm.get(['room'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResident>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
