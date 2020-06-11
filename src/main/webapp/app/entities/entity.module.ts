import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'facility',
        loadChildren: () => import('./facility/facility.module').then(m => m.FacilityFacilityModule),
      },
      {
        path: 'resident',
        loadChildren: () => import('./resident/resident.module').then(m => m.FacilityResidentModule),
      },
      {
        path: 'room',
        loadChildren: () => import('./room/room.module').then(m => m.FacilityRoomModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class FacilityEntityModule {}
