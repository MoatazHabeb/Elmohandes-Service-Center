import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { TeamService } from "../../../service/team.service";
import { AccountService } from "../../../service/account.service";
import { AccountDto } from "../../../model/account-dto";
import { Team } from "../../../model/team";

@Component({
  selector: 'app-team-actions',
  templateUrl: './team-actions.component.html',
  styleUrls: ['./team-actions.component.css']
})
export class TeamActionsComponent implements OnInit {
  actionKey!: string;
  teamId!: number;

  teamData?: Team;
  accountList: AccountDto[] = [];
  newBalance: AccountDto = {
    reason: '',
    newBalance: 0,
    dateOfReason: '',
    team_id: 0
  };

  constructor(
    private route: ActivatedRoute,
    private teamService: TeamService,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.actionKey = params.get('key')!;
      this.teamId = Number(params.get('teamId'));

      if (this.actionKey === 'update') {
        this.loadTeam();
      } else if (this.actionKey === 'create-balance') {
        const today = new Date().toISOString().split('T')[0];
        this.newBalance = {
          reason: '',
          newBalance: 0,
          dateOfReason: today,
          team_id: this.teamId
        };
      } else if (this.actionKey === 'show-balance') {
        this.loadBalances();
      }
    });
  }

  loadTeam(): void {
    this.teamService.getTeamById(this.teamId).subscribe(team => {
      this.teamData = team;
    });
  }

  loadBalances(): void {
    this.accountService.getAccount(this.teamId).subscribe(data => {
      this.accountList = data;
    });
  }

  updateTeam(): void {
    if (this.teamData) {
      this.teamService.updateTeam(this.teamData).subscribe(() => {
        alert('Updated successfully!');
        this.teamData = {
          name: '',
          phoneNumber: '',
          age: '',
          image: '',
          balance: 0,
          active: true
        };
      });
    }
  }

  createBalance(): void {
    this.accountService.createBalance(this.newBalance).subscribe(() => {
      alert('Balance created!');
      const today = new Date().toISOString().split('T')[0];
      this.newBalance = {
        reason: '',
        newBalance: 0,
        dateOfReason: today,
        team_id: this.teamId
      };
    });
  }
}
