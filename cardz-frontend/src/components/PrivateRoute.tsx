import { Navigate, Outlet } from "react-router-dom";
import { isAuthenticated } from "../services/auth.service"

export function PrivateRoute() {
  if (!isAuthenticated()) {
    return <Navigate to="/" replace />;
  }

  return <Outlet />;
}
