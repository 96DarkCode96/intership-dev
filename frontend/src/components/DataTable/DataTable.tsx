import './DataTable.css';
import {useTranslation} from "react-i18next";
import Filter from "../Filter/Filter.tsx";
import RankingCard from "../RankingCard/RankingCard.tsx";
import {DataRow} from "../../hooks/useFetchData.ts";

interface DataTableProps {
    leaderboard: DataRow[];
}

export function DataTable({leaderboard, filter, setFilter}: Readonly<DataTableProps>) {
    const {t} = useTranslation();

    return (
        <div className="data-table-container">
            <h1 className="data-table-title">{t('business_man_ranking')}</h1>
            <Filter filter={filter} setFilter={setFilter}/>
            <div className="h-px w-full bg-black my-2"></div>
            <section className="flex flex-row gap-2 overflow-x-hidden">
                {leaderboard ? leaderboard.splice(0, 6).map((data, index) => (
                    <RankingCard key={index} data={data} index={index}/>
                )) : (
                    <>
                    </>
                )}
            </section>
            <section className="border border-gray-100 rounded-xl">
                {leaderboard.slice(6).map((data, index) => {
                    // Calculate the actual overall rank in the leaderboard
                    const actualIndex = index + 6;

                    return (
                        <div key={data.id || actualIndex} className="p-2 border-b last:border-0">
                            <span className="mr-2 font-bold">#{actualIndex + 1}</span>
                            {data.fullName}
                        </div>
                    );
                })}
            </section>
        </div>
    );
}
