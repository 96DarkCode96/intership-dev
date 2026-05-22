import {useState, useEffect} from 'react';
import {Person} from "../types.ts";

export interface DataRow {
    fullName: string;
    totalProfit: number;
    trendPercentage: number;
    numberOfCases: number;
    id: number;
}

export function useFetchData() {
    const [data, setData] = useState<DataRow[]>([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await fetch('http://10.10.64.153:8080/api/people?year=2026&month=1');
            if (!response.ok) {
                throw new Error('Failed to fetch data');
            }
            const result: Person[] = await response.json();
            console.log(result);
            setData(result);
        } catch (err) {
            setData([]);
        }
    };

    return {data};
}
