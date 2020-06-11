import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ResidentService } from 'app/entities/resident/resident.service';
import { IResident, Resident } from 'app/shared/model/resident.model';

describe('Service Tests', () => {
  describe('Resident Service', () => {
    let injector: TestBed;
    let service: ResidentService;
    let httpMock: HttpTestingController;
    let elemDefault: IResident;
    let expectedResult: IResident | IResident[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ResidentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Resident(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            creationDate: currentDate.format(DATE_TIME_FORMAT),
            modificationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Resident', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            creationDate: currentDate.format(DATE_TIME_FORMAT),
            modificationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            creationDate: currentDate,
            modificationDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Resident()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Resident', () => {
        const returnedFromService = Object.assign(
          {
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            email: 'BBBBBB',
            creationDate: currentDate.format(DATE_TIME_FORMAT),
            modificationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            creationDate: currentDate,
            modificationDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Resident', () => {
        const returnedFromService = Object.assign(
          {
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            email: 'BBBBBB',
            creationDate: currentDate.format(DATE_TIME_FORMAT),
            modificationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            creationDate: currentDate,
            modificationDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Resident', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
