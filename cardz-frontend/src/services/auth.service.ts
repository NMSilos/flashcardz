import { jwtDecode } from 'jwt-decode'
import { api } from './api'
import type { TokenPayload } from '../types/jwt'

export type LoginCredentials = {
  email: string
  password: string
}

export type RegisterCredentials = {
  username: string
  email: string
  password: string
}

export const loginRequest = (data: LoginCredentials) => {
  return api.post('/api/users/login', data)
}

export const registerRequest = (data: RegisterCredentials) => {
  return api.post('/api/users/register', data);
}

export const getPayloadFromToken = () => {
  const token = localStorage.getItem('token');
  if (token !== null) {
    const payload = jwtDecode<TokenPayload>(token);
    return payload;
  }
  return null;
}

export function isAuthenticated() {
  return !!localStorage.getItem('token');
}