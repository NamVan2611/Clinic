export interface StatCardItem {
  title: string;
  value: string;
  subtitle: string;
  icon: string;
  iconColor: string;
  iconBg: string;
}

export interface VisitBar {
  label: string;
  value: number;
}

export interface CalendarDate {
  day: string;
  variant?: 'muted' | 'highlight' | 'primary';
}

export interface ScheduleItem {
  title: string;
  time: string;
  tone: 'primary' | 'tertiary';
}

export interface AppointmentItem {
  patient: string;
  doctor: string;
  date: string;
  status: 'Confirmed' | 'In Progress' | 'Cancelled';
}

export interface PatientItem {
  name: string;
  id: string;
  age: string;
  avatar: string;
}

export const statCards: StatCardItem[] = [
  {
    title: 'Total Doctors',
    value: '24',
    subtitle: '+2 this month',
    icon: 'medical_services',
    iconColor: 'text-primary',
    iconBg: 'bg-primary/10'
  },
  {
    title: 'Total Patients',
    value: '1.2k',
    subtitle: '+12% vs last week',
    icon: 'group',
    iconColor: 'text-secondary',
    iconBg: 'bg-secondary/10'
  },
  {
    title: "Today's Appointments",
    value: '18',
    subtitle: '4 remaining',
    icon: 'calendar_today',
    iconColor: 'text-tertiary',
    iconBg: 'bg-tertiary/10'
  },
  {
    title: 'Active Prescriptions',
    value: '45',
    subtitle: 'Steady trend',
    icon: 'description',
    iconColor: 'text-on-surface-variant',
    iconBg: 'bg-surface-variant'
  }
];

export const visitBars: VisitBar[] = [
  { label: 'Mon', value: 60 },
  { label: 'Tue', value: 85 },
  { label: 'Wed', value: 45 },
  { label: 'Thu', value: 70 },
  { label: 'Fri', value: 95 },
  { label: 'Sat', value: 30 },
  { label: 'Sun', value: 15 }
];

export const calendarDates: CalendarDate[] = [
  { day: '28', variant: 'muted' },
  { day: '29', variant: 'muted' },
  { day: '30', variant: 'muted' },
  { day: '1' },
  { day: '2' },
  { day: '3', variant: 'highlight' },
  { day: '4' },
  { day: '5' },
  { day: '6' },
  { day: '7', variant: 'primary' },
  { day: '8' },
  { day: '9' },
  { day: '10' },
  { day: '11' }
];

export const scheduleItems: ScheduleItem[] = [
  {
    title: 'Surgery Review',
    time: '09:00 AM - 10:30 AM',
    tone: 'primary'
  },
  {
    title: 'Client Consultation',
    time: '11:00 AM - 12:00 PM',
    tone: 'tertiary'
  }
];

export const recentAppointments: AppointmentItem[] = [
  {
    patient: 'Alice Johnson',
    doctor: 'Dr. Sarah Connor',
    date: 'Oct 24, 2023',
    status: 'Confirmed'
  },
  {
    patient: 'Michael Chen',
    doctor: 'Dr. James Wilson',
    date: 'Oct 24, 2023',
    status: 'In Progress'
  },
  {
    patient: 'Elena Rodriguez',
    doctor: 'Dr. Emily Blunt',
    date: 'Oct 23, 2023',
    status: 'Cancelled'
  },
  {
    patient: 'Robert T.',
    doctor: 'Dr. Sarah Connor',
    date: 'Oct 23, 2023',
    status: 'Confirmed'
  }
];

export const recentPatients: PatientItem[] = [
  {
    name: 'Alice Johnson',
    id: '#PT-9042',
    age: '28 years',
    avatar:
      'https://lh3.googleusercontent.com/aida/ADBb0ujz-dECOtzGHn6FiMDmqVJg6Askl30oDzObI1PTF0UfuNeKr4TbQ3ca5vq_AZl81O2PJfVFpudeUXCkQcEq8Jujm9xnpNjRYW-JQys9K8zapYUctmbaZdQKqt8slSF9xBnGk6rRCBVWOktzPagywt-D2QKI2lGVU8Yj4MY8hOb2--n2SmtKbA9ZRFGpvo5LChGL9h9I2xV1ww9_PRVazAW6RuJEdGePT6UxJCPr4DlEUDCIT8NCDKCIiw'
  },
  {
    name: 'Michael Chen',
    id: '#PT-8831',
    age: '34 years',
    avatar:
      'https://lh3.googleusercontent.com/aida/ADBb0ugd0hElNMdPjs692zc7dcyifRTlGhnOzg3FUB6QBXqpkU8CDxGHjkAHzH98Lb_7HYH6Mf6jSqn_hzq1XId0RDqUsA5teCcDi3is8tOwPutj2g3-0tdaIUBlCAu-thjcfUbrhjGcS1TPMlypVUBRLOasMEDoG76pX7a4miaj9SvFEj_UjhWFjeCnQ4CDLyuOHXjtLPkcug9UFV3_KN932p5tDqHGC-53XsJg1rXZsy0Ko6jqEA'
  },
  {
    name: 'Elena Rodriguez',
    id: '#PT-7712',
    age: '65 years',
    avatar:
      'https://lh3.googleusercontent.com/aida/ADBb0uiUY-TDrb0euXChMp6h1QiYUpIpB5mE64ldYNSHvLpskukFpxPqnvtI7UWjzYPTKREw82VE50e4H91pVeek129l_7IAVszd5JNzxxzQQHrdpdYVZxc2CdDc1wpipT5tC7founN6fS1i8_5wxDCdf-yzdztU0Xj8eJtN-uvQEe-5MM5nSxFov3R7a0u5CWak0xNzz1hTQcY6tA1RhWTzzd7_BTpHeGToaoIZFBzV5hYZ7gsPWoBuqQ0U4A'
  },
  {
    name: 'Robert T.',
    id: '#PT-9011',
    age: '42 years',
    avatar:
      'https://lh3.googleusercontent.com/aida/ADBb0ugx65yXyz1bbaoED73jbB4vSXVP-DIKuX412s08fVKcOdy7m3RXZHlZfxKKsqjGT6mHc5DpE5ymzBnQYOEggpvmO4JhOYs19gjuErPSogeSsA1nYMAwjWy5LZurGoNcCzenc8aMRY8pH0ksEUrPTc5_tn2Bjg3qrah_-N3bNjNAmPRm-qWoXfWQGHybVh4RTzEzQ_AbgKrdJbwCBAh6WBbqbBroaytuaKYxRIqTGcvIg'
  }
];
