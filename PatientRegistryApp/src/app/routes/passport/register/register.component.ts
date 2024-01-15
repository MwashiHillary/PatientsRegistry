import { AuthService } from '../../../core/auth.service';
import { HttpContext } from '@angular/common/http';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnDestroy } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ALLOW_ANONYMOUS } from '@delon/auth';
import { I18nPipe, _HttpClient } from '@delon/theme';
import { MatchControl } from '@delon/util/form';
import { NzAlertModule } from 'ng-zorro-antd/alert';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzSafeAny } from 'ng-zorro-antd/core/types';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { NzProgressModule } from 'ng-zorro-antd/progress';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { finalize } from 'rxjs';

@Component({
  selector: 'passport-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  standalone: true,
  imports: [
    ReactiveFormsModule,
    I18nPipe,
    RouterLink,
    NzAlertModule,
    NzFormModule,
    NzInputModule,
    NzPopoverModule,
    NzProgressModule,
    NzSelectModule,
    NzGridModule,
    NzButtonModule
  ]
})
export class UserRegisterComponent implements OnDestroy {
  static form: any;
  static loading: boolean;
  static cdr: any;
  static http: any;
  static router: any;
  interval$: any;
  static auth: AuthService;
  static username: string;
  static email: string;
  static password: string;
  static errorMessage: string;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private http: _HttpClient,
    private cdr: ChangeDetectorRef,
    private auth:AuthService
  ) {}

  // #region fields

  form = this.fb.nonNullable.group(
    {
      username: ['', [Validators.required, Validators.name]],
      mail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), UserRegisterComponent.checkPassword.bind(this)]],
      confirm: ['', [Validators.required, Validators.minLength(6)]]
    },
    {
      validators: MatchControl('password', 'confirm')
    }
  );
  error = '';
  type = 0;
  loading = false;
  visible = false;
  status = 'pool';
  progress = 0;
  passwordProgressMap: { [key: string]: 'success' | 'normal' | 'exception' } = {
    ok: 'success',
    pass: 'normal',
    pool: 'exception'
  };

  // #endregion

  static checkPassword(control: FormControl): NzSafeAny {
    if (!control) {
      return null;
  }

  submit(): void {
    this.error = '';
    Object.keys(this.form.controls).forEach(key => {
      const control = (this.form.controls as NzSafeAny)[key] as AbstractControl;
      control.markAsDirty();
      control.updateValueAndValidity();
    });
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.cdr.detectChanges();
    this.auth.attemptAuth({ username: this.username, email: this.email, password: this.password }).subscribe(
      (data) => {
        // console.log(data);
        this.router.navigate(['register-result']);
      },
      (err) => {
        // console.log(err);
        this.errorMessage = 'sign in failed';
        return;
      },

    );
  }

  ngOnDestroy(): void {
    if (this.interval$) {
      clearInterval(this.interval$);
    }
  }
}
