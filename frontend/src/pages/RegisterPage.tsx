import { FormEvent, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const RegisterPage = () => {
  const auth = useAuth();
  const navigate = useNavigate();
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [termsAccepted, setTermsAccepted] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (!fullName.trim() || !email.trim() || !password || !confirmPassword) {
      setError('Please fill in all fields.');
      return;
    }

    if (password !== confirmPassword) {
      setError('Passwords do not match.');
      return;
    }

    if (!termsAccepted) {
      setError('You must agree to the terms and privacy policy.');
      return;
    }

    setError('');
    setLoading(true);

    await auth.signIn({ email, password, remember: true });
    setLoading(false);
    navigate('/login');
  };

  return (
    <main className="bg-background min-h-screen flex items-center justify-center p-6 overflow-x-hidden">
      <div className="w-full max-w-[1100px] grid grid-cols-1 md:grid-cols-2 glass-card rounded-[24px] shadow-2xl overflow-hidden min-h-[700px] bg-white">
        <section className="hidden md:flex flex-col justify-between p-12 relative bg-surface-container-low overflow-hidden">
          <div className="relative z-10">
            <div className="flex items-center gap-2 mb-12">
              <span className="material-symbols-outlined text-primary text-3xl" style={{ fontVariationSettings: "'FILL' 1" }}>
                medical_services
              </span>
              <h1 className="font-display text-display text-primary tracking-tight">MediFlow Pro</h1>
            </div>
            <div className="space-y-6">
              <h2 className="font-headline-lg text-headline-lg text-on-surface max-w-[320px]">
                Join the next generation of clinical excellence.
              </h2>
              <p className="font-body-md text-body-md text-on-surface-variant max-w-[340px]">
                Our unified platform connects healthcare professionals with real-time patient data, automated scheduling, and intelligent prescription management.
              </p>
            </div>
          </div>

          <div className="relative z-10">
            <div className="flex items-center gap-4 p-4 rounded-xl bg-white/60 border border-outline-variant/30 backdrop-blur-sm">
              <div className="w-10 h-10 rounded-full overflow-hidden bg-primary-container/20 flex items-center justify-center">
                <span className="material-symbols-outlined text-primary">verified_user</span>
              </div>
              <div>
                <p className="font-label-md text-label-md text-on-surface">HIPAA Compliant</p>
                <p className="font-label-sm text-label-sm text-on-surface-variant">Enterprise-grade security standards</p>
              </div>
            </div>
          </div>

          <div className="absolute inset-0 z-0 opacity-40">
            <img
              alt="Medical background"
              className="w-full h-full object-cover grayscale"
              src="https://lh3.googleusercontent.com/aida/ADBb0uiibs0_Yfb61sMB3Ca-_ridWjo-UgZut3e1NoxrM2TYaRIZjqtyKmtbb7yBNZGCRGRkJJFzvuy_C7zc6L_BwrVJwZ2msLNDrWSXLoRp0AOeC40QrDSO9uwlP1W3_RaV30gRolwOOF6Inq9r1Gr5CW2MdshCbZxZMWl0JeDJhoG-UYhlr5UTOBAGkkErDdVWw8RB4bjvZZUSuTokQr-GI3kOEfsFXtwiYor0av4JozA8c2XL31UnbSiR0w"
            />
          </div>
        </section>

        <section className="flex flex-col justify-center p-8 md:p-16 bg-white">
          <div className="w-full max-w-sm mx-auto space-y-8">
            <div className="space-y-2">
              <h2 className="font-headline-lg text-headline-lg text-on-surface">Create Account</h2>
              <p className="font-body-md text-body-md text-on-surface-variant">Get started by setting up your staff credentials.</p>
            </div>

            <form className="space-y-5" onSubmit={handleSubmit} noValidate>
              <div className="space-y-1.5">
                <label className="font-label-md text-label-md text-on-surface-variant block" htmlFor="fullname">
                  Full Name
                </label>
                <div className="relative group">
                  <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px] transition-colors group-focus-within:text-primary">
                    person
                  </span>
                  <input
                    id="fullname"
                    type="text"
                    value={fullName}
                    onChange={(event) => setFullName(event.target.value)}
                    placeholder="Dr. Julian Smith"
                    className="w-full pl-10 pr-4 py-2.5 rounded-lg border border-outline-variant bg-surface font-body-md text-body-md input-focus-ring transition-all placeholder:text-outline/50"
                  />
                </div>
              </div>

              <div className="space-y-1.5">
                <label className="font-label-md text-label-md text-on-surface-variant block" htmlFor="email">
                  Email Address
                </label>
                <div className="relative group">
                  <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px] transition-colors group-focus-within:text-primary">
                    mail
                  </span>
                  <input
                    id="email"
                    type="email"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                    placeholder="julian@clinic.mediflow.com"
                    className="w-full pl-10 pr-4 py-2.5 rounded-lg border border-outline-variant bg-surface font-body-md text-body-md input-focus-ring transition-all placeholder:text-outline/50"
                  />
                </div>
              </div>

              <div className="grid grid-cols-1 gap-5">
                <div className="space-y-1.5">
                  <label className="font-label-md text-label-md text-on-surface-variant block" htmlFor="password">
                    Password
                  </label>
                  <div className="relative group">
                    <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px] transition-colors group-focus-within:text-primary">
                      lock
                    </span>
                    <input
                      id="password"
                      type="password"
                      value={password}
                      onChange={(event) => setPassword(event.target.value)}
                      placeholder="••••••••"
                      className="w-full pl-10 pr-10 py-2.5 rounded-lg border border-outline-variant bg-surface font-body-md text-body-md input-focus-ring transition-all placeholder:text-outline/50"
                    />
                    <button className="absolute right-3 top-1/2 -translate-y-1/2 text-outline hover:text-primary transition-colors" type="button" aria-label="Toggle password visibility">
                      <span className="material-symbols-outlined text-[20px]">visibility</span>
                    </button>
                  </div>
                </div>

                <div className="space-y-1.5">
                  <label className="font-label-md text-label-md text-on-surface-variant block" htmlFor="confirm-password">
                    Confirm Password
                  </label>
                  <div className="relative group">
                    <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px] transition-colors group-focus-within:text-primary">
                      lock_reset
                    </span>
                    <input
                      id="confirm-password"
                      type="password"
                      value={confirmPassword}
                      onChange={(event) => setConfirmPassword(event.target.value)}
                      placeholder="••••••••"
                      className="w-full pl-10 pr-4 py-2.5 rounded-lg border border-outline-variant bg-surface font-body-md text-body-md input-focus-ring transition-all placeholder:text-outline/50"
                    />
                  </div>
                </div>
              </div>

              <div className="flex items-start gap-3">
                <div className="flex items-center h-5">
                  <input
                    id="terms"
                    type="checkbox"
                    checked={termsAccepted}
                    onChange={(event) => setTermsAccepted(event.target.checked)}
                    className="h-4 w-4 rounded border-outline-variant text-primary focus:ring-primary"
                  />
                </div>
                <label className="font-label-sm text-label-sm text-on-surface-variant" htmlFor="terms">
                  I agree to the{' '}
                  <a className="text-primary hover:underline" href="#">
                    Terms of Service
                  </a>{' '}
                  and{' '}
                  <a className="text-primary hover:underline" href="#">
                    Privacy Policy
                  </a>.
                </label>
              </div>

              {error ? <p className="font-body-md text-body-md text-error">{error}</p> : null}

              <button
                type="submit"
                className="w-full bg-secondary text-white font-headline-sm text-headline-sm py-3.5 rounded-lg hover:bg-on-secondary-container active:scale-[0.98] transition-all flex items-center justify-center gap-2 shadow-sm shadow-secondary/20"
              >
                {loading ? (
                  'Registering...'
                ) : (
                  <>
                    Register
                    <span className="material-symbols-outlined text-[20px]">arrow_forward</span>
                  </>
                )}
              </button>
            </form>

            <div className="pt-4 text-center">
              <p className="font-body-md text-body-md text-on-surface-variant">
                Already have an account?
                <Link className="text-primary font-label-md font-semibold hover:underline decoration-2 underline-offset-4 ml-1" to="/login">
                  Sign in
                </Link>
              </p>
            </div>
          </div>
        </section>
      </div>
    </main>
  );
};

export default RegisterPage;
