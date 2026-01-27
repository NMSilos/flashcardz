import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import AppLayout from "../layouts/AppLayout"
import { getDeckById } from "../services/deck.service"
import CreateCardModal from "../components/modals/CreateCardModal"

type Card = {
  id: string
  front: string
  back: string
}

export default function DeckPage() {
  const { deckId } = useParams();

  const [deckName, setDeckName] = useState("Nome do Deck");
  const [cards, setCards] = useState<Card[]>([]);
  const [isCreateCardOpen, setIsCreateCardOpen] = useState(false);

  async function fetchDeckData(deckId: string) {
    const response = await getDeckById(deckId);
    console.log(response.data);
    setDeckName(response.data.name);
    setCards(response.data.cards);
  }

  useEffect(() => {
    fetchDeckData(deckId!);
  }, [deckId, isCreateCardOpen])

  return (
    <AppLayout>
      {/* Header */}
      <div className="mb-8 flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold">
            {deckName}
          </h1>
          <p className="text-slate-400">
            {cards.length} cartas neste deck
          </p>
        </div>

        <div className="flex gap-3">
          <PrimaryButton label="Estudar" />
          <SecondaryButton label="Adicionar carta" onClick={() => setIsCreateCardOpen(true)}/>
        </div>
      </div>

      {/* Cards list */}
      <div className="bg-slate-800 rounded-2xl p-6 shadow">
        <h2 className="text-lg font-semibold mb-4">
          Cartas
        </h2>

        {cards.length === 0 ? (
            <EmptyState />
            ) : (
            <div
                className="
                grid
                grid-cols-1
                sm:grid-cols-2
                md:grid-cols-3
                lg:grid-cols-4
                xl:grid-cols-5
                gap-4
                max-h-[520px]
                overflow-y-auto
                pr-2
                "
            >
            {cards.map((card, index) => (
              <CardItem
                key={card.id}
                index={index + 1}
                front={card.front}
                back={card.back}
              />
            ))}
          </div>
        )}
      </div>
      <CreateCardModal open={isCreateCardOpen} onClose={() => setIsCreateCardOpen(false)} />
    </AppLayout>
  )
}

function CardItem({
  index,
  front,
  back,
}: {
  index: number
  front: string
  back: string
}) {
  return (
    <div className="
      p-4 rounded-xl
      bg-slate-900 hover:bg-slate-700
      transition cursor-pointer
    ">
      <div className="flex items-start justify-between gap-4">
        <div>
          <span className="text-slate-400 text-sm">
            #{index}
          </span>
          <h3 className="font-medium mt-1">
            {front}
          </h3>
          <p className="text-slate-400 text-sm mt-1">
            {back}
          </p>
        </div>

        <span className="text-slate-500 hover:text-sky-400 transition">
          ⋮
        </span>
      </div>
    </div>
  )
}

function EmptyState() {
  return (
    <div className="
      py-16 flex flex-col items-center justify-center
      text-center text-slate-400
    ">
      <span className="text-4xl mb-4">🗂️</span>
      <p className="font-medium">
        Este deck ainda não tem cartas
      </p>
      <p className="text-sm mt-1">
        Comece adicionando sua primeira carta
      </p>
    </div>
  )
}

function PrimaryButton({ label }: { label: string }) {
  return (
    <button
      className="
        px-6 py-3 rounded-xl font-semibold
        bg-sky-500 text-slate-900 hover:bg-sky-400
        transition
      "
    >
      {label}
    </button>
  )
}

function SecondaryButton({ label, onClick }: { label: string; onClick?: () => void }) {
  return (
    <button
      className="
        px-6 py-3 rounded-xl font-semibold
        bg-slate-700 text-slate-100 hover:bg-slate-600
        transition
      "
      onClick={onClick}
    >
      {label}
    </button>
  )
}

