import { Component, Input, OnInit } from '@angular/core';
import { PeopleListComponent } from '../people-list/people-list.component';
import { Person } from '../person';
import { PersonService } from '../person.service';


@Component({
  selector: 'person-details',
  templateUrl: './person-details.component.html',
  styleUrls: ['./person-details.component.css']
})
export class PersonDetailsComponent implements OnInit {

  @Input() person: Person;

  constructor(private personService: PersonService, private listComponent: PeopleListComponent) { }

  ngOnInit() {
  }

  deletePerson() {
    this.personService.deletePerson(this.person.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }
}
