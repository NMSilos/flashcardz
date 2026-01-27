export type Card = {
  id: string
  front: string
  back: string
  deckId: string
}

export type CreateCardData = {
  front: string
  back: string
  deckId: string
}