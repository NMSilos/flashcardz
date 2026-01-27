import type { Card } from "./Card"

export type Deck = {
  id: string
  name: string
  userId: string
  creationDate: string
  cards: Card[]
}

export type CreateDeckData = {
  name: string
  user: { id: string }
}