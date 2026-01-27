import type { Card } from "./Card"

export type Deck = {
  id: string
  name: string
  userId: string
  creationDate: string
  cards: Card[]
}