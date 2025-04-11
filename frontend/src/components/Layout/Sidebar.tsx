import Link from "next/link";

export default function Sidebar() {
  return (
    <aside className="w-64 bg-gray-900 text-white h-screen p-4 overflow-y-auto">
      <nav>
        <ul>
          <li className="mb-2">
            <Link
              href="/dashboard"
              className="block hover:bg-gray-700 p-2 rounded"
            >
              Dashboard
            </Link>
          </li>
          <li className="mb-2">
            <Link href="/banks" className="block hover:bg-gray-700 p-2 rounded">
              Bancos
            </Link>
          </li>
          <li className="mb-2">
            <Link href="/admin" className="block hover:bg-gray-700 p-2 rounded">
              Admin
            </Link>
          </li>
        </ul>
      </nav>
    </aside>
  );
}
