export interface TokenPayload {
  iss: string
  sub: string
  id: string
  username: string
  exp: number
}

export type Deck = {
  id: string
  name: string
  userId: string
  creationDate: string
  cards: Card[]
}

export type Card = {
  id: string
  front: string
  back: string
  deckId: string
}
