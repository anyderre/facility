import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFacility } from 'app/shared/model/facility.model';

type EntityResponseType = HttpResponse<IFacility>;
type EntityArrayResponseType = HttpResponse<IFacility[]>;

@Injectable({ providedIn: 'root' })
export class FacilityService {
  public resourceUrl = SERVER_API_URL + 'api/facilities';

  constructor(protected http: HttpClient) {}

  create(facility: IFacility): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(facility);
    return this.http
      .post<IFacility>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(facility: IFacility): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(facility);
    return this.http
      .put<IFacility>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFacility>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFacility[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(facility: IFacility): IFacility {
    const copy: IFacility = Object.assign({}, facility, {
      creationDate: facility.creationDate && facility.creationDate.isValid() ? facility.creationDate.toJSON() : undefined,
      modificationDate: facility.modificationDate && facility.modificationDate.isValid() ? facility.modificationDate.toJSON() : undefined,
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
      res.body.forEach((facility: IFacility) => {
        facility.creationDate = facility.creationDate ? moment(facility.creationDate) : undefined;
        facility.modificationDate = facility.modificationDate ? moment(facility.modificationDate) : undefined;
      });
    }
    return res;
  }
}
