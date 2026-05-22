import {useTranslation} from "react-i18next";
import {DataRow} from "../../hooks/useFetchData.ts";

const styles = {
    0: {
        'bg-card': 'bg-orange-400',
        'text-deals': 'text-orange-400',
        'bg-inner': 'bg-orange-100',
        'border': 'border-orange-400',
        'text-rank': 'text-white'
    },
    1: {
        'bg-card': 'bg-gray-400',
        'text-deals': 'text-gray-400',
        'bg-inner': 'bg-gray-100',
        'border': 'border-gray-400',
        'text-rank': 'text-white'
    },
    2: {
        'bg-card': 'bg-orange-600',
        'text-deals': 'text-orange-400',
        'bg-inner': 'bg-gray-100',
        'border': 'border-gray-400',
        'text-rank': 'text-white'
    },
    3: {
        'bg-card': 'bg-gray-200',
        'text-deals': 'text-gray-600',
        'bg-inner': 'bg-gray-50',
        'border': 'border-gray-200',
        'text-rank': 'text-gray-600'
    },
} as Record<number, Record<string, string>>;

export default function RankingCard({
                                        data, index
                                    }: Readonly<{
    data: DataRow;
    index: number;
}>) {

    const {t} = useTranslation();

    const style = styles[index] ?? styles[3];

    console.log(data);

    return (
        <div className={`flex flex-col rounded-xl overflow-hidden border ${style['border']}`}>
            <div className={`${style['bg-card']} px-3 py-2 flex flex-row items-center`}>
                <p className={`${style['text-rank']} uppercase text-xs font-semibold`}>{t("ranking", {number: index + 1})}</p>
                <div className="flex flex-row bg-white rounded-md px-2 py-0.5 ml-auto">
                    <p className="text-[0.6rem]">{data.trendPercentage}%</p>
                </div>
            </div>
            <div className={`${style['bg-inner']} p-3 flex flex-col items-center gap-2 h-full`}>
                <img className={`${style['bg-card']} rounded-full w-14 p-1`}
                     src={`https://api.dicebear.com/7.x/lorelei/svg?seed=${data.fullName}`} alt="John's Avatar"/>
                <p className="font-bold text-center text-xs">{data.fullName}</p>
                <div className="grid grid-cols-2 w-max min-w-48 rounded-md border bg-gray-200 gap-px border-gray-200">
                    <div className="flex flex-col items-center justify-center py-2 px-4 bg-white rounded-l-md">
                        <p className={`${style['text-deals']} font-bold`}>{data.numberOfCases}</p>
                        <p className="text-xs text-black/70">{t("deals")}</p>
                    </div>
                    <div className="flex flex-col items-center justify-center py-2 px-4 bg-white rounded-r-md">
                        <p className="font-bold text-[0.75rem]">{new Intl.NumberFormat('cs-CZ').format(data.totalProfit)}</p>
                        <p className="text-xs text-black/70">{t("currency.czech")}</p>
                    </div>
                </div>
            </div>
        </div>
    );
}