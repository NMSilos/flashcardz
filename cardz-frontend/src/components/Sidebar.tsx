import { useState } from "react";
import { Home, BookOpen, Plus, LogOut, Menu } from "lucide-react";
import { useNavigate } from "react-router";

export default function Sidebar() {
  const [collapsed, setCollapsed] = useState(false);
  const navigate = useNavigate();
  return (
    <aside
      className={`
        h-screen bg-slate-950 border-r border-slate-800
        flex flex-col transition-all duration-300
        ${collapsed ? "w-20" : "w-64"}
      `}
    >
      {/* Header */}
      <div className="flex items-center justify-between p-4">
        {!collapsed && (
          <h1 className="text-slate-100 font-bold text-xl">
            Flashcardz
          </h1>
        )}

        <button
          onClick={() => setCollapsed(!collapsed)}
          className="
            text-slate-400 hover:text-sky-400
            p-2 rounded-lg hover:bg-slate-800
            transition
          "
        >
          <Menu size={20} />
        </button>
      </div>

      {/* Menu */}
      <nav className="flex-1 px-2 space-y-1">
        <SidebarItem icon={<Home />} label="Dashboard" collapsed={collapsed} />
        <SidebarItem icon={<BookOpen />} label="Meus Decks" collapsed={collapsed} />
        <SidebarItem icon={<Plus />} label="Novo Deck" collapsed={collapsed} />
      </nav>

      {/* Footer */}
      <div className="p-2">
        <SidebarItem
          icon={<LogOut />}
          label="Sair"
          collapsed={collapsed}
          danger
        />
      </div>
    </aside>
  );
}

function SidebarItem({
  icon,
  label,
  collapsed,
  danger = false,
}: {
  icon: React.ReactNode;
  label: string;
  collapsed: boolean;
  danger?: boolean;
}) {

  const navigate = useNavigate();

  return (
    <button
      className={`
        w-full flex items-center gap-3
        px-3 py-3 rounded-xl
        text-sm font-medium
        transition
        ${
          danger
            ? "text-red-400 hover:bg-red-500/10"
            : "text-slate-300 hover:bg-slate-800 hover:text-sky-400"
        }
      `}
    onClick={() => {
      localStorage.removeItem("token");
      navigate("/");
    }}
    >
      <span className="text-lg">{icon}</span>
      {!collapsed && <span>{label}</span>}
    </button>
  );
}
