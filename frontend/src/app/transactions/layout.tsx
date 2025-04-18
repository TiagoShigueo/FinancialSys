import Sidebar from "@/components/Layout/Sidebar";

export default function TransactionsLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex min-h-screen">
      <Sidebar />
      <main className="flex-1 p-6"> {children}</main>
    </div>
  );
}
