import type { MouseEventHandler } from 'react';

interface SidebarProps {
  onLogout: MouseEventHandler<HTMLButtonElement>;
}

const navigation = [
  { label: 'Dashboard', icon: 'dashboard', active: true },
  { label: 'Doctors', icon: 'medical_services' },
  { label: 'Patients', icon: 'group' },
  { label: 'Appointments', icon: 'calendar_today' },
  { label: 'Prescriptions', icon: 'description' },
  { label: 'Settings', icon: 'settings' }
];

const Sidebar = ({ onLogout }: SidebarProps) => (
  <aside className="fixed left-0 top-0 h-screen w-[260px] bg-surface border-r border-outline-variant flex flex-col py-6 z-50">
    <div className="px-6 mb-8">
      <h1 className="font-headline-sm text-headline-sm font-bold text-primary">MediFlow Pro</h1>
      <p className="font-label-sm text-label-sm text-outline">Clinic Management</p>
    </div>

    <nav className="flex-grow">
      {navigation.map((item) => (
        <a
          key={item.label}
          className={`flex items-center gap-3 px-4 py-3 mx-2 rounded-lg transition-colors duration-150 ${
            item.active
              ? 'text-primary bg-surface-container-high border-l-2 border-primary opacity-90'
              : 'text-on-surface-variant hover:bg-surface-container'
          }`}
          href="#"
        >
          <span className="material-symbols-outlined">{item.icon}</span>
          <span className="font-label-md text-label-md">{item.label}</span>
        </a>
      ))}
    </nav>

    <div className="px-2 mt-auto">
      <button
        type="button"
        onClick={onLogout}
        className="flex w-full items-center gap-3 px-4 py-3 rounded-lg text-on-surface-variant hover:bg-surface-container transition-colors"
      >
        <span className="material-symbols-outlined">logout</span>
        <span className="font-label-md text-label-md">Logout</span>
      </button>
    </div>
  </aside>
);

export default Sidebar;
