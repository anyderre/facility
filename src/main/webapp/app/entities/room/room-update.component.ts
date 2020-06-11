import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRoom, Room } from 'app/shared/model/room.model';
import { RoomService } from './room.service';
import { IFacility } from 'app/shared/model/facility.model';
import { FacilityService } from 'app/entities/facility/facility.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

type SelectableEntity = IFacility | IUser;

@Component({
  selector: 'jhi-room-update',
  templateUrl: './room-update.component.html',
})
export class RoomUpdateComponent implements OnInit {
  isSaving = false;
  facilities: IFacility[] = [];
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    roomNumber: [null, [Validators.required]],
    floor: [],
    numberOfBed: [],
    capacity: [],
    creationDate: [],
    modificationDate: [],
    facility: [],
    user: [],
  });

  constructor(
    protected roomService: RoomService,
    protected facilityService: FacilityService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ room }) => {
      if (!room.id) {
        const today = moment().startOf('day');
        room.creationDate = today;
        room.modificationDate = today;
      }

      this.updateForm(room);

      this.facilityService.query().subscribe((res: HttpResponse<IFacility[]>) => (this.facilities = res.body || []));

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(room: IRoom): void {
    this.editForm.patchValue({
      id: room.id,
      roomNumber: room.roomNumber,
      floor: room.floor,
      numberOfBed: room.numberOfBed,
      capacity: room.capacity,
      creationDate: room.creationDate ? room.creationDate.format(DATE_TIME_FORMAT) : null,
      modificationDate: room.modificationDate ? room.modificationDate.format(DATE_TIME_FORMAT) : null,
      facility: room.facility,
      user: room.user,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const room = this.createFromForm();
    if (room.id !== undefined) {
      this.subscribeToSaveResponse(this.roomService.update(room));
    } else {
      this.subscribeToSaveResponse(this.roomService.create(room));
    }
  }

  private createFromForm(): IRoom {
    return {
      ...new Room(),
      id: this.editForm.get(['id'])!.value,
      roomNumber: this.editForm.get(['roomNumber'])!.value,
      floor: this.editForm.get(['floor'])!.value,
      numberOfBed: this.editForm.get(['numberOfBed'])!.value,
      capacity: this.editForm.get(['capacity'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value
        ? moment(this.editForm.get(['creationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      modificationDate: this.editForm.get(['modificationDate'])!.value
        ? moment(this.editForm.get(['modificationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      facility: this.editForm.get(['facility'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRoom>>): void {
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
