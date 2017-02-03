import {Component} from 'angular2/core';

@Component({
  selector: 'meu-app',
  templateUrl: 'app/views/app.html'
})
export class AppComponent {
  // ATRIBUTOS
  // interpolação
  public olaFulano: string = 'ola aline!';

  // atribuições
  public mostrarMensagem: boolean = true;

  // one way e two way binding
  public nome: string = 'Fulano';
  public nome2: string = 'Ciclano';

  // css class
  public azul: boolean = true;
  public cadastrar: boolean = true;
  public remover: boolean = false;

  // *ngIf
  public exibir: boolean = true;
  public contador: number = 1;

  // *ngFor
public clientes: string[] = ['Ciclano', 'Fulano', 'Beltrano'];

  // MÉTODOS
  // interpolação
  obterJuros(): number {
    return 100;
  }


  // eventos
  dizerOi(): void {
    alert(this.olaFulano);
  }

  mostrarTexto($event): void {
    alert($event.target.value);
  }


  // atribuições
  mudarMostrarMensagem(): void {
    this.mostrarMensagem = !this.mostrarMensagem;
  }

  // one way e two way binding

  atualizarNome($event): void {
    this.nome = $event.target.value;
  }

  // css class
  mudarEstilo(): void {
    this.azul = !this.azul;
  }

  alterarCadastro(): void {
    this.cadastrar = !this.cadastrar;
    this.remover = !this.remover;
  }

  // *ngIf
  alternarValor(): void {
    this.exibir = !this.exibir;
  }

  incrementar(): void {
    this.contador++;
  }

}
