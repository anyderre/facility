import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IRoom } from 'app/shared/model/room.model';

export interface IResident {
  id?: number;
  firstName?: string;
  lastName?: string;
  phoneNumber?: string;
  email?: string;
  creationDate?: Moment;
  modificationDate?: Moment;
  user?: IUser;
  room?: IRoom;
}

export class Resident implements IResident {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public phoneNumber?: string,
    public email?: string,
    public creationDate?: Moment,
    public modificationDate?: Moment,
    public user?: IUser,
    public room?: IRoom
  ) {}
}
