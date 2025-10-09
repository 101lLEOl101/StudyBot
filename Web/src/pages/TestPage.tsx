import {HeaderComponent} from "../components/HeaderComponent.tsx";
import {Paper} from "@mantine/core";
import TestComponent from "../components/TestComponent.tsx";

export default function TestPage() {
    return (
        <>
            <HeaderComponent active_page={4}/>
            <Paper withBorder maw={1200} ml={"auto"} mr={"auto"} mb={50} p={10}>
                <TestComponent/>
            </Paper>
        </>
    )
}
