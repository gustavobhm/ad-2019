import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from '../person';
import { PersonService } from '../person.service';
import { UserService } from '../user.service';
import { User } from '../user';


@Component({
  selector: 'people-list',
  templateUrl: './people-list.component.html',
  styleUrls: ['./people-list.component.css']
})
export class PeopleListComponent implements OnInit {

  people: Observable<Person[]>;

  names: string[];

  constructor(private personService: PersonService, private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
  }

  deletePeople() {
    this.personService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('ERROR: ' + error));
  }

  reloadData() {
    this.people = this.personService.getPeople();

    this.people.subscribe(
      peopleData => {
        this.names = peopleData.map(person => person.name);
      });
  }

  shufflePeopleAndSendEmail() {
    let scrambledNames = this.shuffleArray(this.names);
    this.updateListWith(scrambledNames);
  }

  shuffleArray(names) {
    let a = names.slice(0);
    let b = names.slice(0);
    let result = [];
    while (a.length > 1) {
      let i = this.extractRandomElement(a);
      let j = this.extractRandomElement(b);

      while (i === j) {
        b.push(j);
        j = this.extractRandomElement(b);
      }
      result.push({ a: i, b: j });
    }
    if (a[0] === b[0]) {
      result.push({ a: a[0], b: result[0].b });
      result[0].b = a[0];
    } else {
      result.push({ a: a[0], b: b[0] });
    }
    return result.map(function (item) { return item.a + ',' + item.b });

  }

  extractRandomElement(array) {
    return array.splice(Math.floor(Math.random() * array.length), 1)[0];
  }

  updateListWith(scrambledNames) {

    scrambledNames.forEach(item => {

      let splitted = item.split(",", 2);

      this.personService.getPersonByName(splitted[0])
        .subscribe(person => {
          person.friend = splitted[1];
          this.sendEmail(person);
          this.personService.updatePerson(person.id, person)
            .subscribe(res => { 
              this.reloadData(); 
            });
        });

    });
  }

  sendEmail(person){

    let user = new User();
    user.emailAddress=person.email;
    user.firstName="";
    user.lastName="";
    user.subject="Secret Santa";
    user.message=person.name + " your secret santa is " + person.friend;

    this.userService.sendEmail(user).subscribe();

  }


}
