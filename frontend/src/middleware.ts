import { NextRequest, NextResponse } from "next/server";
import { jwtVerify } from "jose";

const secret = new TextEncoder().encode(process.env.JWT_SECRET);

async function getUserRole(token: string) {
  try {
    const { payload } = await jwtVerify(token, secret);
    return payload.role as string;
  } catch (e) {
    return null;
  }
}

export async function middleware(request: NextRequest) {
  const token = request.cookies.get("token")?.value;

  if (!token) {
    return NextResponse.redirect(new URL("/login", request.url));
  }

  const role = await getUserRole(token);

  if (request.nextUrl.pathname.startsWith("/admin") && role !== "Admin") {
    // colocar as páginas de admin na pasta admin
    return NextResponse.redirect(new URL("/unauthorized", request.url));
  }

  return NextResponse.next();
}

export const config = {
  matcher: ["/dashboard/:path*", "/admin/:path*"],
};
