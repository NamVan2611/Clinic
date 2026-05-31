import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const summary = [
  { label: 'Upcoming appointments', value: '18', delta: '+12%' },
  { label: 'Active patients', value: '324', delta: '+8%' },
  { label: 'Prescriptions filled', value: '86', delta: '+5%' }
];

const features = [
  { title: 'Smart scheduling', description: 'Automatically optimize your calendar and reduce patient wait time.' },
  { title: 'Patient records', description: 'Securely store patient history, documents, and treatment plans.' },
  { title: 'Prescription workflows', description: 'Generate, approve, and send prescriptions in a single flow.' }
];

const DashboardPage = () => {
  const auth = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    auth.signOut();
    navigate('/', { replace: true });
  };

  return (
    <main className="min-h-screen bg-background text-on-background">
      <div className="mx-auto max-w-[1140px] px-6 py-8 lg:px-10">
        <header className="flex flex-col gap-6 lg:flex-row lg:items-center lg:justify-between">
          <div>
            <div className="inline-flex items-center gap-3 rounded-2xl bg-primary-container/10 px-4 py-2 text-primary">
              <span className="material-symbols-outlined">medical_services</span>
              <span className="font-semibold">MediFlow Pro</span>
            </div>
            <h1 className="mt-6 text-3xl font-semibold leading-tight text-on-background">Clinic operations designed for modern care teams.</h1>
            <p className="mt-3 max-w-2xl font-body-lg text-slate-600">
              Welcome back, {auth.userEmail}. Monitor care workflows, patient activity, and prescription delivery from a single polished workspace.
            </p>
          </div>

          <div className="flex flex-col gap-3 sm:flex-row sm:items-center">
            <button
              type="button"
              onClick={handleLogout}
              className="inline-flex items-center justify-center rounded-xl border border-outline px-5 py-3 text-sm font-semibold text-on-surface transition hover:bg-surface-container"
            >
              Sign Out
            </button>
            <button
              type="button"
              className="inline-flex items-center justify-center rounded-xl bg-primary-container px-5 py-3 text-sm font-semibold text-white shadow-lg shadow-primary-container/20 transition hover:brightness-110"
            >
              Schedule a follow-up
            </button>
          </div>
        </header>

        <section className="mt-10 grid gap-6 lg:grid-cols-3">
          {summary.map((item) => (
            <article key={item.label} className="rounded-3xl border border-outline-variant/60 bg-white p-6 shadow-sm">
              <p className="font-label-sm text-label-sm uppercase tracking-[0.22em] text-slate-500">{item.label}</p>
              <div className="mt-4 flex items-end justify-between gap-4">
                <p className="text-3xl font-semibold text-on-background">{item.value}</p>
                <span className="rounded-full bg-primary-container/10 px-3 py-1 text-sm font-semibold text-primary">{item.delta}</span>
              </div>
            </article>
          ))}
        </section>

        <section className="mt-10 grid gap-6 lg:grid-cols-[1.4fr_0.9fr]">
          <div className="rounded-[28px] bg-surface-container-high p-8 shadow-lg shadow-slate-200/60">
            <div className="flex items-center justify-between gap-4">
              <div>
                <p className="font-label-sm text-label-sm uppercase tracking-[0.22em] text-slate-500">Today&apos;s priority</p>
                <h2 className="mt-3 text-2xl font-semibold text-on-background">Review patient charts and refill authorizations</h2>
              </div>
              <span className="inline-flex h-12 w-12 items-center justify-center rounded-3xl bg-primary/10 text-primary">
                <span className="material-symbols-outlined">check_circle</span>
              </span>
            </div>
            <div className="mt-8 grid gap-4 sm:grid-cols-2">
              <div className="rounded-3xl bg-white p-5 shadow-sm">
                <p className="font-label-sm text-label-sm uppercase tracking-[0.18em] text-slate-500">Waiting rooms</p>
                <p className="mt-3 text-xl font-semibold text-on-background">4 rooms ready</p>
              </div>
              <div className="rounded-3xl bg-white p-5 shadow-sm">
                <p className="font-label-sm text-label-sm uppercase tracking-[0.18em] text-slate-500">Messages</p>
                <p className="mt-3 text-xl font-semibold text-on-background">12 unread</p>
              </div>
            </div>
          </div>

          <aside className="space-y-6">
            {features.map((feature) => (
              <div key={feature.title} className="rounded-[28px] bg-white p-6 shadow-sm border border-outline-variant/60">
                <p className="font-headline-sm text-headline-sm text-on-background">{feature.title}</p>
                <p className="mt-3 font-body-md text-body-md text-slate-600">{feature.description}</p>
              </div>
            ))}
          </aside>
        </section>

        <section className="mt-10 grid gap-6 lg:grid-cols-3">
          <div className="rounded-[28px] bg-primary-container p-6 text-white shadow-lg shadow-primary-container/20">
            <p className="font-label-sm uppercase tracking-[0.22em] opacity-80">Team efficiency</p>
            <h3 className="mt-3 text-2xl font-semibold">Drive faster consultations</h3>
            <p className="mt-4 font-body-md text-white/90">Keep every patient touchpoint connected across care coordinators, nurses, and physicians.</p>
          </div>
          <div className="rounded-[28px] bg-surface-container-high p-6 shadow-sm">
            <p className="font-label-sm uppercase tracking-[0.22em] text-slate-500">Patient experience</p>
            <h3 className="mt-3 text-xl font-semibold text-on-background">Personalized care at scale</h3>
            <p className="mt-4 font-body-md text-slate-600">Structured patient check-ins, shared care plans, and faster follow-ups.</p>
          </div>
          <div className="rounded-[28px] bg-surface-container-high p-6 shadow-sm">
            <p className="font-label-sm uppercase tracking-[0.22em] text-slate-500">Security</p>
            <h3 className="mt-3 text-xl font-semibold text-on-background">Built for compliance</h3>
            <p className="mt-4 font-body-md text-slate-600">Role-aware access controls and audit-ready activity logs keep your clinic secure.</p>
          </div>
        </section>
      </div>
    </main>
  );
};

export default DashboardPage;
