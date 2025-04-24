import Navbar from "@/components/Layout/Navbar";
import Sidebar from "@/components/Layout/Sidebar";

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="min-h-screen flex">
      <Sidebar />
      <div className="flex flex-col flex-1">
        <Navbar />
        <main className="flex-1 p-6"> {children}</main>
      </div>
    </div>
  );
}
