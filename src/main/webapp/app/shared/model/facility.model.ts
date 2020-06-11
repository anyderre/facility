import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IFacility {
  id?: number;
  description?: string;
  area?: number;
  quantity?: number;
  creationDate?: Moment;
  modificationDate?: Moment;
  user?: IUser;
}

export class Facility implements IFacility {
  constructor(
    public id?: number,
    public description?: string,
    public area?: number,
    public quantity?: number,
    public creationDate?: Moment,
    public modificationDate?: Moment,
    public user?: IUser
  ) {}
}
