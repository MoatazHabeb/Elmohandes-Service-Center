import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Team} from "../model/team";

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private baseUrl = 'http://localhost:1050'; // adjust if your base path differs

  constructor(private http: HttpClient) {}

  getAllTeam(): Observable<Team[]> {
    return this.http.get<Team[]>(`${this.baseUrl}/getallteam`);
  }

  createTeamMember(team: Team): Observable<Team> {
    return this.http.post<Team>(`${this.baseUrl}/creatememberofteam`, team);
  }
  getTeamById(id: number): Observable<Team> {
    return this.http.get<Team>(`${this.baseUrl}/getteambyid/${id}`);
  }

  updateTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(`${this.baseUrl}/UpdateTeam`, team);
  }
}
