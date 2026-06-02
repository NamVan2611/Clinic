interface TopNavProps {
  searchTerm: string;
  onSearchChange: (value: string) => void;
  userName: string;
  userRole: string;
}

const TopNav = ({ searchTerm, onSearchChange, userName, userRole }: TopNavProps) => (
  <header className="h-16 flex justify-between items-center px-container-padding fixed top-0 right-0 w-[calc(100%-260px)] bg-surface/80 backdrop-blur-md border-b border-outline-variant z-40">
    <div className="flex items-center bg-surface-container-low px-4 py-1.5 rounded-full glass-border w-full max-w-[560px]">
      <span className="material-symbols-outlined text-outline text-[20px] mr-2">search</span>
      <input
        value={searchTerm}
        onChange={(event) => onSearchChange(event.target.value)}
        placeholder="Search patients, appointments..."
        className="bg-transparent border-none focus:ring-0 text-label-md w-full p-0 placeholder:text-outline"
        type="text"
        aria-label="Search clinic data"
      />
    </div>

    <div className="flex items-center gap-6">
      <button type="button" className="relative text-on-surface-variant hover:text-primary transition-colors" aria-label="View notifications">
        <span className="material-symbols-outlined">notifications</span>
        <span className="absolute top-0 right-0 w-2 h-2 bg-error rounded-full" />
      </button>
      <button type="button" className="text-on-surface-variant hover:text-primary transition-colors" aria-label="Get help">
        <span className="material-symbols-outlined">help</span>
      </button>
      <div className="flex items-center gap-3 pl-4 border-l border-outline-variant">
        <div className="text-right hidden sm:block">
          <p className="font-label-md text-label-md text-on-surface">{userName}</p>
          <p className="text-[11px] text-outline">{userRole}</p>
        </div>
        <img
          src="https://lh3.googleusercontent.com/aida/ADBb0ugyE_LF7oDTTRcIpvtM_bgd4d6KqfMGbyxzMeBPbR5XsNJQAY7dYytM0l03sGnNUNDaZQDgNfCMRPoWDB-J9Swo3jjmyawLETW8UXjGn74B6_JGfqcTJl4RIIV8clI47F2axn4b7Qcv1KT8LaAoU1Eqdq0l10xBcwpmmpHRRaStJQxVcb-qzOv2LXilzg6_WYPjY1bvYvdJ3kIlyNU01jtgKJ_HgYIDK9OpjZkaK_2qFSogvRgfYFlX"
          alt="User profile"
          className="w-10 h-10 rounded-full object-cover ring-2 ring-primary-container"
        />
      </div>
    </div>
  </header>
);

export default TopNav;
