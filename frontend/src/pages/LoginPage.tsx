import { FormEvent, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import Particles from '../components/Particles';

const LoginPage = () => {
  const auth = useAuth();
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [remember, setRemember] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (!email.trim() || !password) {
      setError('Please enter your email and password.');
      return;
    }
    setError('');
    setLoading(true);

    await auth.signIn({ email, password, remember });
    navigate('/dashboard');
  };

  return (
    <main className="relative mx-auto w-full max-w-[1100px] min-h-[720px] flex overflow-hidden rounded-[24px] bg-surface-container-lowest shadow-2xl">
      <section className="w-full lg:w-[480px] h-full flex flex-col p-12 z-10 bg-surface-container-lowest">
        <div className="mb-12 flex items-center gap-3">
          <div className="w-12 h-12 bg-primary-container rounded-xl flex items-center justify-center text-white shadow-sm">
            <span className="material-symbols-outlined">medical_services</span>
          </div>
          <div>
            <h1 className="font-headline-sm text-headline-sm font-bold text-primary">MediFlow Pro</h1>
            <p className="font-label-sm text-label-sm text-outline uppercase tracking-[0.3em]">Clinic Management</p>
          </div>
        </div>

        <header className="mb-10">
          <h2 className="font-headline-lg text-headline-lg text-on-background mb-2">Welcome Back</h2>
          <p className="font-body-md text-body-md text-on-surface-variant">
            Access your clinical dashboard and patient records.
          </p>
        </header>

        <form className="flex flex-col gap-6" onSubmit={handleSubmit} noValidate>
          <div className="flex flex-col gap-2">
            <label htmlFor="email" className="font-label-md text-label-md text-on-surface">
              Email Address
            </label>
            <div className="relative">
              <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline">mail</span>
              <input
                id="email"
                type="email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                placeholder="dr.smith@mediflow.com"
                className="w-full pl-10 pr-4 py-3 bg-surface-container border border-outline-variant rounded-lg font-body-md text-on-surface focus:ring-2 focus:ring-primary-container focus:border-primary-container transition-all outline-none"
                autoComplete="email"
              />
            </div>
          </div>

          <div className="flex flex-col gap-2">
            <label htmlFor="password" className="font-label-md text-label-md text-on-surface">
              Password
            </label>
            <div className="relative">
              <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline">lock</span>
              <input
                id="password"
                type="password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                placeholder="••••••••"
                className="w-full pl-10 pr-12 py-3 bg-surface-container border border-outline-variant rounded-lg font-body-md text-on-surface focus:ring-2 focus:ring-primary-container focus:border-primary-container transition-all outline-none"
                autoComplete="current-password"
              />
              <button type="button" className="absolute right-3 top-1/2 -translate-y-1/2 text-outline hover:text-primary transition-colors" aria-label="Toggle password visibility">
                <span className="material-symbols-outlined">visibility</span>
              </button>
            </div>
          </div>

          <div className="flex flex-col gap-3">
            <div className="flex items-center justify-between">
              <label className="flex items-center gap-2 cursor-pointer group">
                <input
                  type="checkbox"
                  checked={remember}
                  onChange={(event) => setRemember(event.target.checked)}
                  className="w-4 h-4 rounded border-outline-variant text-primary-container focus:ring-primary-container"
                />
                <span className="font-label-md text-label-md text-on-surface-variant group-hover:text-on-surface transition-colors">
                  Remember me
                </span>
              </label>
              <a className="font-label-md text-label-md text-primary font-semibold hover:underline" href="#">
                Forgot password?
              </a>
            </div>
            {error ? <p className="font-body-md text-body-md text-error">{error}</p> : null}
            <button
              type="submit"
              className="w-full py-4 bg-primary-container text-white font-headline-sm rounded-lg hover:brightness-110 active:scale-[0.98] transition-all shadow-lg shadow-primary-container/20 flex items-center justify-center gap-2"
              disabled={loading}
            >
              {loading ? (
                <>
                  <span className="material-symbols-outlined animate-spin">progress_activity</span>
                  Authenticating...
                </>
              ) : (
                <>
                  Sign In
                  <span className="material-symbols-outlined">arrow_forward</span>
                </>
              )}
            </button>
          </div>
        </form>

        <footer className="mt-auto pt-8 border-t border-outline-variant/30 text-center">
          <p className="font-body-md text-body-md text-on-surface-variant">
            Don&apos;t have an account?{' '}
            <Link
              to="/register"
              className="text-primary font-bold hover:underline"
            >
              Register your clinic
            </Link>
          </p>
        </footer>
      </section>

      <section className="hidden lg:flex flex-1 relative bg-surface-container overflow-hidden">
        <div className="absolute inset-0 pattern-bg" />
        <Particles />
        <div className="relative z-10 w-full h-full flex flex-col items-center justify-center p-12 text-center">
          <div className="absolute inset-0 flex items-center justify-center">
            <div className="w-[520px] h-[520px] bg-primary/5 rounded-full blur-3xl animate-pulse" />
          </div>

          <div className="relative z-20 max-w-md">
            <div className="mb-8 rounded-[24px] overflow-hidden shadow-2xl glass-card p-2 border-2 border-white/50">
              <div className="relative w-full h-[320px] bg-surface rounded-[20px] overflow-hidden">
                <div className="absolute inset-0 bg-[radial-gradient(circle_at_top_left,_rgba(14,165,233,0.24),_transparent_35%),radial-gradient(circle_at_bottom_right,_rgba(13,148,136,0.14),_transparent_35%)]" />
                <div className="absolute inset-0 flex flex-col justify-between p-6">
                  <div className="rounded-3xl bg-white/85 p-4 shadow-sm border border-white/60 max-w-[220px] text-left">
                    <p className="font-label-sm text-label-sm text-slate-500 uppercase tracking-[0.18em]">Patient vitals</p>
                    <p className="mt-3 font-headline-md text-headline-md text-slate-900">82 BPM</p>
                    <span className="font-body-md text-body-md text-slate-500">Heart rate</span>
                  </div>
                  <div className="grid gap-3 text-left">
                    <div className="rounded-3xl bg-white/85 p-4 shadow-sm border border-white/60">
                      <p className="font-label-sm text-label-sm text-slate-500 uppercase">Next appointment</p>
                      <p className="mt-2 font-body-md text-slate-900">Thu, 10:30 AM</p>
                    </div>
                    <div className="rounded-3xl bg-white/85 p-4 shadow-sm border border-white/60">
                      <p className="font-label-sm text-label-sm text-slate-500 uppercase">Prescriptions</p>
                      <p className="mt-2 font-body-md text-slate-900">3 active orders</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <h3 className="font-headline-lg text-headline-lg text-on-background mb-4">
              Precision clinical data at your fingertips.
            </h3>
            <p className="font-body-lg text-body-lg text-on-surface-variant">
              Streamline your practice with MediFlow Pro&apos;s advanced patient scheduling and automated prescription management.
            </p>
            <div className="flex flex-col items-center gap-4 mt-10 sm:flex-row sm:justify-center">
              <div className="flex -space-x-3">
                <span className="w-10 h-10 rounded-full border-2 border-surface bg-primary/10 shadow-sm flex items-center justify-center text-primary font-semibold">AM</span>
                <span className="w-10 h-10 rounded-full border-2 border-surface bg-secondary/10 shadow-sm flex items-center justify-center text-secondary font-semibold">EL</span>
                <span className="w-10 h-10 rounded-full border-2 border-surface bg-primary-container/10 shadow-sm flex items-center justify-center text-primary-container font-semibold">JP</span>
              </div>
              <p className="font-label-md text-label-md text-on-surface-variant flex items-center">
                Join 2,000+ medical professionals
              </p>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
};

export default LoginPage;
