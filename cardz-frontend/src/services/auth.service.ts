import { api } from './api'

export type LoginCredentials = {
  email: string
  password: string
}

export const loginRequest = (data: LoginCredentials) => {
    return api.post('/api/users/login', data)
}