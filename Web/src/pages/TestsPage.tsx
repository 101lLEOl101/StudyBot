import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Paper} from "@mantine/core";
import TestsComponent from "../components/TestsComponent.tsx";

export default function TestsPage() {
    return (
        <>
            <HeaderComponent active_page={4}/>
            <Paper withBorder maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <TestsComponent/>
            </Paper>
        </>
    )
}
