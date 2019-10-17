import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../person.service';


@Component({
  selector: 'new-person',
  templateUrl: './new-person.component.html',
  styleUrls: ['./new-person.component.css']
})
export class NewPessoaComponent implements OnInit {

  person: Person = new Person();
  submitted = false;

  constructor(private personService: PersonService) { }

  ngOnInit() {
  }

  newPessoa(): void {
    this.submitted = false;
    this.person = new Person();
  }

  save() {
    this.personService.createPerson(this.person)
      .subscribe(data => console.log(data), error => console.log(error));
    this.person = new Person();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
