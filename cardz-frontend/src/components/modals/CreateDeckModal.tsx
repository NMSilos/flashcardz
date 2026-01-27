import { useState } from "react";
import { createDeck } from "../../services/deck.service";
import Swal from "sweetalert2";
import { getPayloadFromToken } from "../../services/auth.service";

type Props = {
    open: boolean;
    onClose: () => void;
}

export default function CreateDeckModal({ open, onClose }: Props) {
    const [name, setName] = useState('');

    if (!open) return null;

    async function create() {
        const payload = getPayloadFromToken();
        const response = await createDeck({ name, user: { id: payload!.id }});
        if (response.status === 201) {
            setName('');
            onClose();
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Erro ao criar deck',
                text: "Tente novamente mais tarde.",
            });
        }
    }

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center">
        {/* Overlay */}
        <div
            className="absolute inset-0 bg-black/60"
            onClick={onClose}
        />

        {/* Modal */}
        <div className="
            relative z-10 w-full max-w-md
            bg-slate-800 rounded-2xl p-6 shadow-xl
        ">
            <h2 className="text-xl font-bold mb-4">
            Criar novo deck
            </h2>

            <div className="space-y-4">
            <div>
                <label className="text-sm text-slate-400">
                Nome do deck
                </label>
                <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Ex: Estudo de Inglês"
                className="
                    w-full mt-1 px-4 py-3 rounded-xl
                    bg-slate-900 text-white
                    border border-slate-700
                    focus:outline-none focus:ring-2 focus:ring-sky-500
                "
                />
            </div>

            <div className="flex gap-3 pt-2">
                <button
                onClick={onClose}
                className="
                    flex-1 py-3 rounded-xl
                    bg-slate-700 hover:bg-slate-600
                "
                >
                Cancelar
                </button>

                <button
                className="
                    flex-1 py-3 rounded-xl font-semibold
                    bg-sky-500 text-slate-900 hover:bg-sky-400
                "
                onClick={create}
                >
                Criar
                </button>
            </div>
            </div>
        </div>
        </div>
    );
}