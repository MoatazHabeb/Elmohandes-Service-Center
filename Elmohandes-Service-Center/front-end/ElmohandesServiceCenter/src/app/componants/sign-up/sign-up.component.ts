import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../service/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  messageAr: String = '';
  messageEn: String = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  togglePasswordVisibility(fieldId: string) {
    const field = document.getElementById(fieldId) as HTMLInputElement;
    const toggle = field.nextElementSibling as HTMLElement;

    if (field.type === 'password') {
      field.type = 'text';
      toggle.textContent = '🙈';
    } else {
      field.type = 'password';
      toggle.textContent = '👁️';
    }
  }

  login() {
    this.router.navigateByUrl('/login');
  }

  disablePaste(event: ClipboardEvent) {
    event.preventDefault();
    alert('Pasting is disabled for security reasons');
  }

  disableCopy(event: ClipboardEvent) {
    event.preventDefault();
    alert('Copying and cutting from the password field is disabled for security reasons.');
  }

  createAccount(name: string, phone: string, email: string, password: string, confirmPassword: string, age: string) {
    if (password !== confirmPassword) {
      this.messageEn = 'Passwords do not match.';
      this.messageAr = 'كلمات المرور غير متطابقة.';
      return;
    }

    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
    if (!passwordPattern.test(password)) {
      this.messageEn = 'Password must be at least 8 characters long and include letters, numbers, and special characters.';
      this.messageAr = 'يجب أن تكون كلمة المرور مكونة من 8 أحرف على الأقل وتشمل حروفًا وأرقامًا ورموزًا خاصة.';
      return;
    }

    this.authService.createAccount(name, email, phone, password, age).subscribe(
      () => {
        this.router.navigateByUrl('/login');
      },
      (errorResponse) => {
        this.messageAr = '';
        this.messageEn = '';

        if (errorResponse && typeof errorResponse === 'object') {
          for (const [key, value] of Object.entries(errorResponse)) {
            if (typeof value === 'string') {
              this.messageAr = value;
              this.messageEn = value;
            } else if (typeof value === 'object') {
              // @ts-ignore
              this.messageAr = value.message_ar || '';
              // @ts-ignore
              this.messageEn = value.message_en || '';
            }
          }
        } else {
          this.messageAr = 'An unexpected error occurred';
          this.messageEn = 'An unexpected error occurred';
        }

        this.extracted();
      }
    );
  }

  private extracted() {
    setTimeout(() => {
      this.messageAr = '';
      this.messageEn = '';
    }, 3000);
  }
}
