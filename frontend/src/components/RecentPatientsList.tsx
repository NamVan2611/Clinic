import type { PatientItem } from '../data/dashboardData';

interface RecentPatientsListProps {
  patients: PatientItem[];
}

const RecentPatientsList = ({ patients }: RecentPatientsListProps) => (
  <div className="space-y-6">
    {patients.map((patient) => (
      <div
        key={patient.id}
        className="flex items-center gap-4 group rounded-xl hover:bg-surface-container transition-colors p-2"
      >
        <img src={patient.avatar} alt={`${patient.name} avatar`} className="w-10 h-10 rounded-full glass-border object-cover" />
        <div className="flex-grow">
          <p className="text-label-md font-bold text-on-surface">{patient.name}</p>
          <p className="text-[11px] text-outline">ID: {patient.id} • {patient.age}</p>
        </div>
        <button
          type="button"
          className="w-8 h-8 rounded-full flex items-center justify-center hover:bg-primary/10 text-primary transition-colors"
          aria-label={`View details for ${patient.name}`}
        >
          <span className="material-symbols-outlined text-[20px]">chevron_right</span>
        </button>
      </div>
    ))}
  </div>
);

export default RecentPatientsList;
