"use client";

import { usePathname } from "next/navigation";
import React from "react";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";

export default function LayoutWrapper({
  children,
}: {
  children: React.ReactNode;
}) {
  const pathname = usePathname();
  const isExcluded = ["/login"].includes(pathname);

  if (isExcluded) {
    return <>{children}</>;
  }

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
