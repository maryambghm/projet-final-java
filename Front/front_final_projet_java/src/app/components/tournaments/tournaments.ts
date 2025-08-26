import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-tournaments',
  imports: [RouterModule, DatePipe],
  templateUrl: './tournaments.html',
  styleUrl: './tournaments.css'
})
export class Tournaments {
// tableau vide pour l’instant
  readonly tournaments: Array<{
    id: string;
    date: Date | string;
    name: string;
    type: string;
  }> = [];
}
