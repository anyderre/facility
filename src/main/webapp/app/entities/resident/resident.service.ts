import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IResident } from 'app/shared/model/resident.model';

type EntityResponseType = HttpResponse<IResident>;
type EntityArrayResponseType = HttpResponse<IResident[]>;

@Injectable({ providedIn: 'root' })
export class ResidentService {
  public resourceUrl = SERVER_API_URL + 'api/residents';

  constructor(protected http: HttpClient) {}

  create(resident: IResident): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(resident);
    return this.http
      .post<IResident>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(resident: IResident): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(resident);
    return this.http
      .put<IResident>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IResident>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IResident[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(resident: IResident): IResident {
    const copy: IResident = Object.assign({}, resident, {
      creationDate: resident.creationDate && resident.creationDate.isValid() ? resident.creationDate.toJSON() : undefined,
      modificationDate: resident.modificationDate && resident.modificationDate.isValid() ? resident.modificationDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.creationDate = res.body.creationDate ? moment(res.body.creationDate) : undefined;
      res.body.modificationDate = res.body.modificationDate ? moment(res.body.modificationDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((resident: IResident) => {
        resident.creationDate = resident.creationDate ? moment(resident.creationDate) : undefined;
        resident.modificationDate = resident.modificationDate ? moment(resident.modificationDate) : undefined;
      });
    }
    return res;
  }
}
