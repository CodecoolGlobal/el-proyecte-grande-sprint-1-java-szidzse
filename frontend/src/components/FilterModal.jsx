import React, {useState} from 'react'

const FilterModal = () => {
    const [selectedType, setSelectedType] = useState("");

    const handleSelect = (type) => {
        setSelectedType(type);
    };

    return (
        <>
            <button className="btn" onClick={() => document.getElementById('my_modal_3').showModal()}>
                <img src="/src/assets/filter-horizontal.svg" alt="filter-horizontal.svg"/>Filters
            </button>
            <dialog id="my_modal_3" className="modal">
                <div className="modal-box">
                    <form method="dialog">
                        <button className="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
                    </form>
                    <h3 className="font-bold text-lg">Type of place</h3>
                    <p className="py-4">Search rooms, entire homes, or any type of place</p>
                    <div className="flex flex-col space-y-2">
                        <label className="flex items-center">
                            <input
                                type="radio"
                                name="type"
                                className="radio radio-primary"
                                checked={selectedType === 'Any'}
                                onChange={() => handleSelect('Any')}
                            />
                            <span className="ml-2">Any Type</span>
                        </label>
                        <label className="flex items-center">
                            <input
                                type="radio"
                                name="type"
                                className="radio radio-primary"
                                checked={selectedType === 'Room'}
                                onChange={() => handleSelect('Room')}
                            />
                            <span className="ml-2">Room</span>
                        </label>
                        <label className="flex items-center">
                            <input
                                type="radio"
                                name="type"
                                className="radio radio-primary"
                                checked={selectedType === 'Entire Home'}
                                onChange={() => handleSelect('Entire Home')}
                            />
                            <span className="ml-2">Entire Home</span>
                        </label>
                    </div>
                    <div className="modal-action">
                        <button className="btn btn-primary">Apply</button>
                    </div>
                </div>
            </dialog>
        </>
    )
}
export default FilterModal
