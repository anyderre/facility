import { Moment } from 'moment';
import { IFacility } from 'app/shared/model/facility.model';
import { IUser } from 'app/core/user/user.model';

export interface IRoom {
  id?: number;
  roomNumber?: number;
  floor?: number;
  numberOfBed?: number;
  capacity?: number;
  creationDate?: Moment;
  modificationDate?: Moment;
  facility?: IFacility;
  user?: IUser;
}

export class Room implements IRoom {
  constructor(
    public id?: number,
    public roomNumber?: number,
    public floor?: number,
    public numberOfBed?: number,
    public capacity?: number,
    public creationDate?: Moment,
    public modificationDate?: Moment,
    public facility?: IFacility,
    public user?: IUser
  ) {}
}
