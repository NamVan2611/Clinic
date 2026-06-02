import type { AppointmentItem } from '../data/dashboardData';

interface RecentAppointmentsTableProps {
  appointments: AppointmentItem[];
}

const statusStyles: Record<AppointmentItem['status'], string> = {
  Confirmed: 'bg-secondary-container text-on-secondary-container',
  'In Progress': 'bg-surface-variant text-on-surface-variant',
  Cancelled: 'bg-error-container text-on-error-container'
};

const RecentAppointmentsTable = ({ appointments }: RecentAppointmentsTableProps) => (
  <div className="overflow-x-auto">
    <table className="w-full text-left border-collapse">
      <thead>
        <tr className="bg-surface text-outline font-label-sm text-label-sm">
          <th className="py-3 px-4 first:rounded-l-lg">Patient Name</th>
          <th className="py-3 px-4">Doctor</th>
          <th className="py-3 px-4">Date</th>
          <th className="py-3 px-4">Status</th>
          <th className="py-3 px-4 last:rounded-r-lg text-right">Action</th>
        </tr>
      </thead>
      <tbody className="text-body-md">
        {appointments.map((appointment: AppointmentItem) => (
          <tr
            key={`${appointment.patient}-${appointment.date}`}
            className="border-b border-outline-variant hover:bg-surface transition-colors cursor-pointer group"
          >
            <td className="py-4 px-4 font-bold">{appointment.patient}</td>
            <td className="py-4 px-4">{appointment.doctor}</td>
            <td className="py-4 px-4">{appointment.date}</td>
            <td className="py-4 px-4">
              <span className={`px-2.5 py-1 rounded-full text-[11px] font-bold ${statusStyles[appointment.status]}`}>
                {appointment.status}
              </span>
            </td>
            <td className="py-4 px-4 text-right">
              <button className="material-symbols-outlined text-outline group-hover:text-primary" aria-label="More actions">
                more_vert
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);

export default RecentAppointmentsTable;
