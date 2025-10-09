import {
    Badge,
    Box,
    Button,
    Group,
    Loader,
    Notification,
    Table,
    Text
} from '@mantine/core';
import {stringToColour} from "../stringToColour.ts";
import {useQuery} from "@tanstack/react-query";
import {Link} from "react-router-dom";
import {fetchActiveTests} from "../features/api/apiThunk.ts";
import {ActiveTest} from "../Interfaces.ts";

export default function ActiveTestsComponent() {
    const {data, isLoading, isError, error} = useQuery<ActiveTest[]>({
        queryKey: ["active-tests"],
        queryFn: fetchActiveTests,
    });

    if (isLoading) {
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Loader size="lg" />
            </Box>
        );
    }

    if (isError) {
        return (
            <Notification color="red" title="Ошибка загрузки">
                {error instanceof Error ? error.message : "Неизвестная ошибка"}
            </Notification>
        );
    }

    if (!data || data.length === 0) {
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Text color="dimmed" size="lg">
                    Нет активных тестов
                </Text>
            </Box>
        );
    }

    const rows = data.map((item) => (
        <Table.Tr key={item.id}>
            <Table.Td ta="left">
                <Group gap="sm">
                    <Link to={`/tests/${item.id}`}>
                        <Button variant="default" fz="sm" fw={500}>
                            {item.testName}
                        </Button>
                    </Link>
                </Group>
            </Table.Td>
            <Table.Td ta="center">
                <Badge color={stringToColour(item.disciplineName)}>
                    {item.disciplineName}
                </Badge>
            </Table.Td>
            <Table.Td ta="right">
                <Text size="sm">
                    {item.expiresTime}
                </Text>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Thead>
                    <Table.Tr>
                        <Table.Th ta="left">Название Теста</Table.Th>
                        <Table.Th ta="center">Дисциплина</Table.Th>
                        <Table.Th ta="right">Окончание</Table.Th>
                    </Table.Tr>
                </Table.Thead>
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}
