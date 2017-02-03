import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

@Component({
  selector: 'meu-app',
  template: `
    <nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container">
		<div class="navbar-header">
		  <a class="navbar-brand" href="#">Angular 2</a>
		</div>
		<div>
		  <ul class="nav navbar-nav">
		  	<li><a [routerLink]="['/aluno-listar']">Alunos</a></li>
		  </ul>
		</div>
	  </div>
	</nav>
    <br /><br /><br />
	<router-outlet></router-outlet>
	`,
	directives: [ ROUTER_DIRECTIVES ]
})
export class AppComponent {
}