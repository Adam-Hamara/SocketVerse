import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterModule],
    template: `
      <router-outlet></router-outlet>  <!-- ✅ This renders route components -->
    `,
    styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
