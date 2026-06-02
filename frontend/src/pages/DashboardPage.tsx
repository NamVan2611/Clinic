import { useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import TopNav from '../components/TopNav';
import RecentAppointmentsTable from '../components/RecentAppointmentsTable';
import RecentPatientsList from '../components/RecentPatientsList';
import { useAuth } from '../context/AuthContext';
import {
  AppointmentItem,
  CalendarDate,
  PatientItem,
  ScheduleItem,
  StatCardItem,
  VisitBar,
  calendarDates,
  recentAppointments,
  recentPatients,
  scheduleItems,
  statCards,
  visitBars
} from '../data/dashboardData';

const DashboardPage = () => {
  const auth = useAuth();
  const navigate = useNavigate();
  const [search, setSearch] = useState('');
  const [period, setPeriod] = useState('Last 7 Days');

  const filteredAppointments = useMemo(
    () =>
      recentAppointments.filter((appointment: AppointmentItem) =>
        [appointment.patient, appointment.doctor, appointment.date, appointment.status]
          .join(' ')
          .toLowerCase()
          .includes(search.toLowerCase())
      ),
    [search]
  );

  const filteredPatients = useMemo(
    () =>
      recentPatients.filter((patient: PatientItem) =>
        [patient.name, patient.id, patient.age]
          .join(' ')
          .toLowerCase()
          .includes(search.toLowerCase())
      ),
    [search]
  );

  const handleLogout = () => {
    auth.signOut();
    navigate('/', { replace: true });
  };

  const userName = auth.user?.username ?? 'Dr. Smith';
  const userRole = auth.user?.roles?.includes('ADMIN') ? 'Administrator' : 'Administrator';

  return (
    <main className="min-h-screen bg-surface text-on-surface">
      <Sidebar onLogout={handleLogout} />

      <div className="ml-[260px] min-h-screen">
        <TopNav searchTerm={search} onSearchChange={setSearch} userName={userName} userRole={userRole} />

        <div className="pt-24 pb-container-padding px-container-padding">
          <section className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
            {statCards.map((card: StatCardItem) => (
              <div key={card.title} className="bg-surface-container-lowest p-6 rounded-xl glass-border flex items-center justify-between shadow-sm">
                <div>
                  <p className="text-outline font-label-sm text-label-sm mb-1 uppercase tracking-wider">{card.title}</p>
                  <h3 className="font-headline-lg text-headline-lg text-on-surface">{card.value}</h3>
                  <span className="text-secondary font-label-sm text-[11px] flex items-center mt-1">
                    <span className="material-symbols-outlined text-[14px] mr-1">trending_up</span>
                    {card.subtitle}
                  </span>
                </div>
                <div className={`w-12 h-12 rounded-lg flex items-center justify-center ${card.iconBg} ${card.iconColor}`}>
                  <span className="material-symbols-outlined text-[28px]">{card.icon}</span>
                </div>
              </div>
            ))}
          </section>

          <section className="bento-grid gap-6">
            <div className="col-span-12 lg:col-span-8 bg-surface-container-lowest p-6 rounded-xl glass-border shadow-sm">
              <div className="flex justify-between items-center mb-6">
                <div>
                  <h2 className="font-headline-sm text-headline-sm text-on-surface">Patient Visits</h2>
                  <p className="text-label-sm text-outline">Weekly overview of clinic flow</p>
                </div>
                <select
                  className="bg-surface border border-outline-variant rounded-lg text-label-sm px-3 py-1.5 focus:ring-primary focus:border-primary"
                  value={period}
                  onChange={(event) => setPeriod(event.target.value)}
                >
                  <option>Last 7 Days</option>
                  <option>Last 30 Days</option>
                </select>
              </div>
              <div className="h-[280px] w-full relative flex items-end justify-between gap-2 px-2">
                {visitBars.map((bar: VisitBar) => (
                  <div key={bar.label} className="flex-1 flex flex-col justify-end items-center group">
                    <div
                      className="w-full bg-primary/20 rounded-t-lg transition-all duration-500 hover:bg-primary"
                      style={{ height: `${bar.value}%` }}
                    />
                    <span className="text-[10px] text-outline mt-2">{bar.label}</span>
                  </div>
                ))}
              </div>
            </div>

            <div className="col-span-12 lg:col-span-4 bg-surface-container-lowest p-6 rounded-xl glass-border shadow-sm">
              <div className="flex justify-between items-center mb-6">
                <h2 className="font-headline-sm text-headline-sm text-on-surface">Calendar</h2>
                <button className="text-primary hover:bg-primary/5 p-1 rounded-full transition-colors" type="button" aria-label="Next month">
                  <span className="material-symbols-outlined">chevron_right</span>
                </button>
              </div>
              <div className="grid grid-cols-7 gap-2 text-center mb-4">
                {['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'].map((day) => (
                  <span key={day} className="text-[10px] font-bold text-outline uppercase">
                    {day}
                  </span>
                ))}
              </div>
              <div className="grid grid-cols-7 gap-2 text-center">
                {calendarDates.map((date: CalendarDate) => (
                  <span
                    key={date.day}
                    className={`text-label-sm p-2 rounded-lg ${
                      date.variant === 'muted'
                        ? 'text-outline'
                        : date.variant === 'highlight'
                        ? 'bg-secondary-container text-on-secondary-container rounded-lg font-bold'
                        : date.variant === 'primary'
                        ? 'bg-primary text-on-primary rounded-lg font-bold shadow-md'
                        : 'text-on-surface'
                    }`}
                  >
                    {date.day}
                  </span>
                ))}
              </div>
              <div className="mt-8 pt-6 border-t border-outline-variant">
                <p className="text-label-sm font-bold text-on-surface mb-4">Today's Schedule</p>
                <div className="space-y-4">
                  {scheduleItems.map((item: ScheduleItem) => (
                    <div key={item.title} className="flex items-center gap-3">
                      <div className={`w-1 h-8 rounded-full ${item.tone === 'primary' ? 'bg-primary' : 'bg-tertiary-container'}`} />
                      <div>
                        <p className="text-label-md font-bold text-on-surface">{item.title}</p>
                        <p className="text-[11px] text-outline">{item.time}</p>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            </div>

            <div className="col-span-12 lg:col-span-8 bg-surface-container-lowest p-6 rounded-xl glass-border shadow-sm">
              <div className="flex justify-between items-center mb-6">
                <h2 className="font-headline-sm text-headline-sm text-on-surface">Recent Appointments</h2>
                <button type="button" className="text-primary text-label-md font-bold hover:underline">
                  View All
                </button>
              </div>
              <RecentAppointmentsTable appointments={filteredAppointments} />
            </div>

            <div className="col-span-12 lg:col-span-4 bg-surface-container-lowest p-6 rounded-xl glass-border shadow-sm">
              <div className="flex justify-between items-center mb-6">
                <h2 className="font-headline-sm text-headline-sm text-on-surface">Recent Patients</h2>
                <button
                  type="button"
                  className="text-primary hover:bg-primary/5 p-1 rounded-full transition-colors"
                  aria-label="Add patient"
                >
                  <span className="material-symbols-outlined">person_add</span>
                </button>
              </div>
              <RecentPatientsList patients={filteredPatients} />
            </div>
          </section>

          <div className="mt-12 p-6 rounded-2xl bg-surface-variant/30 border border-outline-variant/50 flex items-center justify-between flex-col gap-6 md:flex-row">
            <div className="flex items-center gap-4">
              <div className="w-12 h-12 rounded-xl bg-surface flex items-center justify-center text-primary">
                <span className="material-symbols-outlined text-3xl">assignment_turned_in</span>
              </div>
              <div>
                <h4 className="font-headline-sm text-headline-sm text-on-surface">Workflow assistant</h4>
                <p className="font-body-md text-body-md text-on-surface-variant">
                  Organize rounds, follow up on prescriptions, and keep patient handoffs seamless.
                </p>
              </div>
            </div>
            <button type="button" className="px-6 py-2 rounded-lg bg-surface-container-highest text-on-surface font-label-md text-label-md hover:bg-primary hover:text-white transition-all">
              Review tasks
            </button>
          </div>
        </div>
      </div>
    </main>
  );
};

export default DashboardPage;
