import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewPessoaComponent } from './new-person/new-person.component';
import { PeopleListComponent } from './people-list/people-list.component';

const routes: Routes = [
    { path: '', redirectTo: 'people', pathMatch: 'full' },
    { path: 'people', component: PeopleListComponent },
    { path: 'new', component: NewPessoaComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }
