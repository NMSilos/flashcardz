import { api } from "./api"

export const getUserByIdRequest = (id: string) => {
  return api.get(`/api/users/${id}`)
}