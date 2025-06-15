import { Component, OnInit } from '@angular/core';
import {TeamService} from "../../../service/team.service";
import {Team} from "../../../model/team";



@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {
  teamList: Team[] = [];

  newMember: Team = {
    name: '',
    phoneNumber: '',
    age: '',
    image: '',
    balance: 0,
    active: true
  };

  showForm: boolean = false;

  constructor(private teamService: TeamService) {}

  ngOnInit(): void {
    this.loadTeam();
  }

  loadTeam(): void {
    this.teamService.getAllTeam().subscribe({
      next: data => this.teamList = data,
      error: err => console.error('Error loading team:', err)
    });
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
  }

  createTeamMember(): void {
    this.teamService.createTeamMember(this.newMember).subscribe({
      next: member => {
        this.teamList.push(member);
        this.newMember = {
          name: '',
          phoneNumber: '',
          age: '',
          image: '',
          balance: 0,
          active: true
        };
        this.showForm = false; // hide form after creation
      },
      error: err => console.error('Error creating member:', err)
    });
  }
  onImageError(event: Event): void {
    (event.target as HTMLImageElement).src = 'assets/images/default.jpg';
  }
}
