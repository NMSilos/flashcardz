import type { CreateCardData } from "../types/Card";
import { api } from "./api";

export function createCard(data: CreateCardData) {
    return api.post('/api/cards/create', data);
}