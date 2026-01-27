
import type { CreateDeckData } from "../types/Deck";
import { api } from "./api";

export function createDeck(data: CreateDeckData) {
    return api.post('/api/decks/create', data);
}

export function getDeckById(deckId: string) {
    return api.get(`/api/decks/${deckId}`);
}